import profilePic from '../Images/UserIcon.png'
import '../Styles/SubmitCard.css'
import React from "react";

function Card(){
    return(
        <div className="submitCard">

                <h2 className="card-title"> Submission </h2>
             <input
                type="url"
                className="card-text"
             />
                <button className="cardButton"> Send</button>


        </div>


    );
}
export default Card



