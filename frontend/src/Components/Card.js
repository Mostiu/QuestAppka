import React from 'react';
import '../Styles/Card.css';

function Card({ title = "Default Title", tags = [], description = "Default Description" }) {
    return (
        <div className="card">
            <div className="dividerC">
                <h2 className="card-title">{title}</h2>
                <p className="tag">{tags.map(tag => `#${tag.name} `)}</p>
            </div>

            <div className="no-whitespace">
                <p className="card-text">{description}</p>
            </div>
        </div>
    );
}

export default Card;