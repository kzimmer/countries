import React from "react";

function CountryEntry(props) {

    render()
    {
        return (
            <form>
                <input
                    className="text-entry"
                    type="text"
                    placeholder="Country name"
                    value={this.props.enteredCountryName}
                    onChange={this.handleTextInputChange}
                />
            </form>

        );
    }
}

export default CountryEntry;