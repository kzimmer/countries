import React, {Component} from "react";
import logo from "./logo.svg";
import "./App.css";
import axios from "axios";

class App extends Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
        this.state = {countries: [], countryName: ''};
    }

    handleChange(event) {
        this.setState({countryName: event.target.value});
    }

    handleSubmit(event) {
        const countryEntered = this.state.countryName;
        alert(countryEntered);
        event.preventDefault();

        axios
            .post("http://127.0.0.1:8080/country/" + countryEntered)
            .then(response => {
                const countryReceived = response.data.map(c => {
                    return {
                        id: c.name,
                        name: c.name,
                        capital: c.capital,
                        population: c.population
                    };
                });
                const newState = Object.assign({}, this.state, {
                    countries: countryReceived
                });
                this.setState(newState);
                console.log("state", this.state.countries);
            })
            .catch(error => console.log(error));

    }

    handleDelete(id) {
        this.setState(prevState => ({
            countries: prevState.countries.filter(el => el != id)
        }));
    }

    componentDidMount() {
        axios
            .get("http://127.0.0.1:8080/countries/")
            .then(response => {
                const countriesReceived = response.data.map(c => {
                    return {
                        id: c.alpha3Code,
                        name: c.name,
                        capital: c.capital,
                        population: c.population
                    };
                });

                // create a new "State" object without mutating the original State object.
                const newState = Object.assign({}, this.state, {
                    countries: countriesReceived
                });

                // store the new state object in the component's state
                this.setState(newState);
                console.log("state", this.state.countries);
            })
            .catch(error => console.log(error));
    }

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                </header>

                <h1>Countries</h1>

                <form onSubmit={this.handleSubmit}>
                    <input type="text" value={this.state.countryName} onChange={this.handleChange}/>
                    <input type="submit" value="Submit"/>
                </form>

                <CountryList countries={this.state.countries} handleDelete={this.handleDelete}/>
                {/*<button onClick={this.handleAddRow}>-</button>*/}

            </div>
        );
    }

}

class CountryList extends React.Component {

    handleDelete(id) {
        this.props.handleDelete(id);
    }

    render() {
        return (
            <ul>
                {this.props.countries.map(c => (
                    <li key={c.id}>{c.name}
                        <button onClick={this.handleDelete.bind(this, c.id)}>-</button>
                    </li>
                ))}
            </ul>
        );
    }
}

export default App;
