import React from 'react';
import '../Styles/TechnologyTile.css'; // Make sure to import or create your CSS file for styling

class TechnologyTile extends React.Component {


    render() {
        const {name} = this.props;
        return (
            <div className="tile">
                <div className="left-half">
                    <h3>{name}</h3>
                </div>
                <div className="divider"></div>
                <div className="right-half">
                    Tags Holder
                </div>
            </div>
        );
    }
}

export default TechnologyTile;