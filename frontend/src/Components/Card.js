import React from 'react';
import '../Styles/Card.css';
import { Link } from 'react-router-dom';

function Card({ title = "Default Title", tags = [], description = "Default Description", contentId, isCourse }) {
    const linkTo = isCourse ? `/courses?content_id=${contentId}` : `/cityChallenge?content_id=${contentId}`;

    return (
        <Link to={linkTo} className="card-link">
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
