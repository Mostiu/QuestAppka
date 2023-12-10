import React from 'react';
import '../Styles/CityChallengeBrowse.css';

const CityChallengeBrowse = () => {
    return (
        <div className={"Challenge"}>
            <h1 className={"challengeTitle"}>The titlest title</h1>
            <p className={"authorText"}>Created by unnamed basist</p>
            <p className={"challengeDescription"}>Challenge description is a long one, but it is, whether one is long is however long the long one is, how long, long enough to long for longing whenever we long for longest of long longing, but so long, long of longs
            </p>
            <input
                type="file"
                accept=".png, .jpg, .jpeg"
                className="fileInput"
            />
            <button className={"submitChallenge"}>Submit</button>
        </div>
    );
}

export default CityChallengeBrowse;
