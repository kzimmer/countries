import React from "react";
import "./Country.css";
import PropTypes from "prop-types";
import CountryEntry from "./CountryEntry";

function Country(props) {
    props.onClick = function () {

    }
    return (
        <div className="country">
            <span>
                <CountryEntry country={props.name}/>
                <button onClick={props.onClick}> --- </button>

                <li key={c.id}>{c.name}
                <button onClick={this.handleDelete.bind(this, c.id)}>-</button>
                </li>
            </span>
        </div>
    );
}

Country.propTypes = {
    name: PropTypes.string.isRequired
};

export default Country;