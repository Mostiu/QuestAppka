import React, {useState} from 'react';
import PropTypes from 'prop-types';
import '../Styles/SubmitCard.css';
import profilePic from '../Images/UserIcon.png';
import axios from "axios";

function SubmitCard({ title, inputPlaceholder, questId, courseId,  }) {
    const [comment, setComment] = useState('');

    const handleButtonClick = async () => {
        const storedToken = localStorage.getItem('jwtToken');
        const mail = localStorage.getItem('mail');

        if (storedToken) {
            try {
                // Make a POST request to update the comment
                console.log(comment);
                await axios.post(
                    `http://localhost:8080/api/users/${mail}/course/${courseId}/quest/${questId}/comment`,
                    comment,
                    {
                        headers: {
                            Authorization: `Bearer ${storedToken}`,
                            'Content-Type': 'application/json',
                        },
                    }
                );

                // Handle success, e.g., show a success message
                console.log('Comment updated successfully');
            } catch (error) {
                // Handle errors, e.g., show an error message
                console.error('Error updating comment:', error);
            }
        }
    };




    return (
        <div className="submitCard">
            <h2 className="card-title">{title}</h2>
            <input
                type="url"
                className="card-text"
                value={comment}
                onChange={(e) => setComment(e.target.value)}
                placeholder={inputPlaceholder}
            />
            <button className="cardButton" onClick={handleButtonClick}>Send</button>
        </div>
    );
}

SubmitCard.propTypes = {
    title: PropTypes.string.isRequired,
    inputPlaceholder: PropTypes.string.isRequired,
};

export default SubmitCard;
