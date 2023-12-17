import React from 'react';
import '../Styles/Card.css';
import { Link } from 'react-router-dom';

function Card({ title = "Default Title", tags = [], description = "Default Description" ,  contentId: contentId, isCourse} ) {
    return (
        <Link to={`/courses?content_id=${contentId}`} className="card-link">
            <div className="card">
                <div className="dividerC">
                    <h2 className="card-title">{title}</h2>
                    <p className="tag">{tags.map((tag) => `#${tag} `)}</p>
                </div>

                <div className="no-whitespace">
                    <p className="card-text">{description}</p>
                </div>
            </div>
        </Link>
    );
}

export default Card;
