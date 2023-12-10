import profilePic from '../Images/UserPic.png'
import '../Styles/ImageCard.css'

function ImageCard({name = "Default Name", email = "Default Email"}){
    return(
        <div className="imageCard">
            <div className= "dividerC">
                <img src={profilePic} alt="profilePic" className="profilePic"/>
            </div>

            <div className="no-whitespace">
                <h2>{name}</h2>
                <p className="card-text"> {email} </p>

            </div>

        </div>


    );
}
export default ImageCard



