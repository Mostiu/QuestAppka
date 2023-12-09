import profilePic from '../Images/UserPic.png'
import '../Styles/ImageCard.css'

function ImageCard(){
    return(
        <div className="imageCard">
            <div className= "dividerC">
                <img src={profilePic} alt="profilePic" className="profilePic"/>
            </div>

            <div className="no-whitespace">
                <h2>Huncho Kermito</h2>
                <p className="card-text"> wordingtonUser@gmail.com </p>

            </div>

        </div>


    );
}
export default ImageCard



