import React from 'react';
import '../Styles/StepTile.css';

class StepTile extends React.Component {


    render() {
        const {name} = this.props;
        return (
            <div className="tileStep">
                <div className="left-half-Step">
                    <h3>{name}</h3>
                </div>
                <div className="divider-Step"></div>
                <div className="right-half-Step">
                    Tags Holder
                </div>
            </div>
        );
    }
}

export default StepTile;