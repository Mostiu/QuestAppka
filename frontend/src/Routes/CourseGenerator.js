import React from 'react';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import '../Styles/CourseGenerator.css';
import TechnologyTile from '../Components/TechnologyTile';

class CourseGenerator extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            technologies: [],
            newTechnology: {
                title: '',
            },
            availableTechnologies: [
                'Technology 1',
                'Technology 2',
                'Technology 3',
                // Add more technologies as needed
            ],
        };
    }

    handleSelectChange = (e) => {
        const { value } = e.target;
        this.setState((prevState) => ({
            newTechnology: {
                ...prevState.newTechnology,
                title: value,
            },
        }));
    };

    handleAddTechnology = () => {
        const { newTechnology, technologies } = this.state;

        if (newTechnology.title) {
            if (!technologies.some((tech) => tech.title === newTechnology.title)) {
                this.setState(
                    (prevState) => ({
                        technologies: [...prevState.technologies, { ...prevState.newTechnology }],
                        newTechnology: {
                            title: '',
                        },
                    }),
                    () => {
                        toast.success('Technology added successfully!');
                    }
                );
            } else {
                toast.warn('Technology is already added.');
            }
        } else {
            toast.error('Please select a technology before adding.');
        }
    };

    render() {
        const { technologies, newTechnology, availableTechnologies } = this.state;

        return (
            <div className={'CGmain'}>
                <div className={'CGLeftContainer'}>
                    <textarea placeholder={'Enter Course Description'}></textarea>
                    <button>Generate Course</button>
                </div>

                <div className={'CGRightContainer'}>
                    <text>Add Technologies</text>
                    {technologies.map((technology) => (
                        <TechnologyTile key={technology.title} name={technology.title} />
                    ))}
                    <div>
                        <select
                            name="title"
                            value={newTechnology.title}
                            onChange={this.handleSelectChange}
                        >
                            <option value="" disabled>
                                Select Technology
                            </option>
                            {availableTechnologies.map((tech) => (
                                <option key={tech} value={tech}>
                                    {tech}
                                </option>
                            ))}
                        </select>

                        <button onClick={this.handleAddTechnology}>Add Technology</button>
                    </div>
                </div>
                <ToastContainer />
            </div>
        );
    }
}

export default CourseGenerator;