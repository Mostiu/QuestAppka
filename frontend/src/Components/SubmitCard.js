import React from 'react';
import PropTypes from 'prop-types';
import '../Styles/SubmitCard.css';
import profilePic from '../Images/UserIcon.png';

function SubmitCard({ title, inputPlaceholder }) {
    return (
        <div className="submitCard">
            <h2 className="card-title">{title}</h2>
            <input
                type="url"
                className="card-text"
                placeholder={inputPlaceholder}
            />
            <button className="cardButton">Send</button>
        </div>
    );
}

SubmitCard.propTypes = {
    title: PropTypes.string.isRequired,
    inputPlaceholder: PropTypes.string.isRequired,
};

export default SubmitCard;
