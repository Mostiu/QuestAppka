import React from 'react';
import '../Styles/TechnologyTile.css'; // Make sure to import or create your CSS file for styling

class TechnologyTile extends React.Component {
    render() {
        const { name, tags } = this.props;

        return (
            <div className="tile">
                <div className="left-half">
                    <h3>{name}</h3>
                </div>
                <div className="divider"></div>
                <div className="right-half">
                    <p className="tag">
                        {tags.map(tag => `#${tag}`).join(', ')}
                    </p>
                </div>
            </div>
        );
    }
}

export default TechnologyTile;
