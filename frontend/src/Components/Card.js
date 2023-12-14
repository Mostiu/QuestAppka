import React from 'react';
import '../Styles/Card.css';
import { Link } from 'react-router-dom';

function Card({ title = "Default Title", tags = [], description = "Default Description" },  courseId ) {
    return (
        <Link to={`/courses?course_id=${courseId}`} className="card-link">
        <div className="card">
            <div className="dividerC">
                <h2 className="card-title">{title}</h2>
                <p className="tag">{tags.map(tag => `#${tag.name} `)}</p>
            </div>

            <div className="no-whitespace">
                <p className="card-text">{description}</p>
            </div>
        </div>
        </Link>
    );
}

export default Card;
