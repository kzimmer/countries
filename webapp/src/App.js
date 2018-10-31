import React, {Component} from "react";
import logo from "./logo.svg";
import "./App.css";
import axios from "axios";

class App extends Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleShowReport = this.handleShowReport.bind(this);
        this.state = {
            countries: [],
            reportCountries: [],
            enteredCountryName: ''
        };
    }

    componentDidMount() {
        axios
            .get("http://127.0.0.1:8080/countries/")
            .then((response) => {
                const countriesReceived = response.data.map(c => {
                    console.log("Capital is " + c.capital);
                    return {
                        id: Date.now(),
                        name: c.name,
                        capital: c.capital,
                        population: c.population
                    };
                });
                this.setState({
                    countries: countriesReceived
                })
            })
            .catch(error => console.log(error));
    }

    handleChange(event) {
        this.setState({enteredCountryName: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        const countryEntered = this.state.enteredCountryName;

        axios
            .post("http://127.0.0.1:8080/country/" + countryEntered)
            .then((response) => {
                    console.log(response.status + " " + response.statusText);

                    let countryReceived = response.data;
                    countryReceived.id = Date.now();
                    console.log("Received country object: " + JSON.stringify(countryReceived, undefined, 2));

                    this.setState((prevState) => ({
                        countries: prevState.countries.concat(countryReceived),
                        enteredCountryName: ""
                    }))
                }
            )
            .catch(error => {
                    console.log(error);
                    alert("HTTP POST error while getting data for " + countryEntered
                    )
                }
            )
    }

    handleDelete(id) {
        const countryName = this.state.countries.find(country => country.id === id).name;
        console.log("Deleting " + countryName);
        this.setState(prevState => ({
            countries: prevState.countries.filter(country => country.id !== id)
        }));
        axios
            .delete("http://127.0.0.1:8080/country/" + countryName)
            .catch(error => console.log(error));

    }

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                </header>
                <h1>Countries</h1>
                <CountryList countries={this.state.countries} handleDelete={this.handleDelete.bind(this)}/>
                <div>
                    <form onSubmit={this.handleSubmit}>
                        <input onChange={this.handleChange} value={this.state.enteredCountryName}/>
                        <button>+</button>
                    </form>
                </div>
                <button onClick={this.handleShowReport}>Page 2</button>
                <button onClick={this.handleClearTable.bind(this)}>Clear table</button>
            </div>
        );
    }

    handleShowReport() {
        axios
            .get("http://127.0.0.1:8080/countries/")
            .then((response) => {
                const countriesReceived = response.data.map(c => {
                    console.log("Capital is " + c.capital);
                    return {
                        id: c.alpha3Code,
                        name: c.name,
                        capital: c.capital,
                        population: c.population
                    };
                });
                this.setState({
                    reportCountries: countriesReceived
                })
            })
            .catch(error => console.log(error));
    }

    handleClearTable() {
        this.setState(() => ({
            countries: [],
            reportCountries: [],
            enteredCountryName: ''
        }));
        axios
            .delete("http://127.0.0.1:8080/countries/")
            .catch(error => console.log((error)));
    }
}

class CountryList
    extends React
        .Component {

    _handleDelete(id) {
        this.props.handleDelete(id);
    }

    render() {
        return (
            <table>
                <tbody>
                {this.props.countries.map(country => (
                    <tr key={country.id}>
                        <td>{country.name}</td>
                        <td>
                            <button onClick={this._handleDelete.bind(this, country.id)}>-</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        );
    }
}

export default App;
