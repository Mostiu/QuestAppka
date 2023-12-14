import profilePic from '../Images/UserIcon.png'
import '../Styles/CourseCard.css'
import PropTypes from "prop-types";

function CourseCard({ title, text }) {
    return (
        <div className="courseCard">
            <h2 className="card-title">{title}</h2>
            <p className="card-text">{text}</p>

        </div>
    );
}

CourseCard.propTypes = {
    title: PropTypes.string.isRequired,
    text: PropTypes.string.isRequired,

};


export default CourseCard



