import React from 'react';
import '../Styles/CourseGenerator.css';
class CourseGenerator extends React.Component {

    render() {

        return(
            <div className={"CGmain"}>
                <div className={"CGRightContainer"}>

                </div>

                <div className={"CGLeftContainer"}>
                    <input type="text" placeholder="Enter Course Name" />
                    <button>Generate Course</button>
                </div>
            </div>



        )
    }
}

export default CourseGenerator;
