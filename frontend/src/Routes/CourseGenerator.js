import React from 'react';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import '../Styles/CourseGenerator.css';
import TechnologyTile from '../Components/TechnologyTile';
import "openai";
import StepTile from "../Components/StepTile";
import axios from "axios";
class CourseGenerator extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            technologies: [],
            generatedLines: [],
            newTechnology: {
                id: null,
                name: '',
                tags: []
            },
            availableTechnologies: [],
            courseTitle: '', // Add this line for the course title
            submittedInfo: null,
        };
    }

    getTechnologiesTags = async (id) => {
        try {
            const storedToken = localStorage.getItem('jwtToken');
            const response = await fetch(`http://localhost:8080/api/technologies/${id}/tags`, {
                headers: {
                    'Authorization': `Bearer ${storedToken}`,
                    'Content-Type': 'application/json',
                },
            });

            const data = await response.json();
            return data;
        } catch (error) {
            console.error('Error fetching tags:', error);
            return [];
        }
    };

    fetchAvailableTechnologies = async () => {
        const storedToken = localStorage.getItem('jwtToken');

        if (storedToken) {
            try {
                const response = await fetch('http://localhost:8080/api/technologies', {
                    headers: {
                        'Authorization': `Bearer ${storedToken}`,
                        'Content-Type': 'application/json',
                    },
                });

                const data = await response.json();

                // Fetch tags for each technology
                const tagsPromises = data.map(async (technology) => {
                    const tags = await this.getTechnologiesTags(technology.id);
                    return { ...technology, tags }; // Combine technology data with tags
                });

                const technologiesWithData = await Promise.all(tagsPromises);
                console.log('technologiesWithData', technologiesWithData)

                this.setState({
                    availableTechnologies: technologiesWithData,
                });
            } catch (error) {
                console.error('Error fetching technologies:', error);
            }
        } else {
            console.error('No token found. User may not be authenticated.');
        }
    };

    componentDidMount() {
        this.fetchAvailableTechnologies();
    }

    handleSelectChange = (e) => {
        const { value, options } = e.target;
        const selectedTechnology = options[options.selectedIndex].dataset;

        // Parse data-tags into an array
        const tagsArray = selectedTechnology.tags ? selectedTechnology.tags.split(', ') : [];

        this.setState((prevState) => ({
            newTechnology: {
                ...prevState.newTechnology,
                id: selectedTechnology.id || null,
                name: selectedTechnology.name || '',
                tags: tagsArray,
            },
        }));
    };


    handleAddTechnology = () => {
        const { newTechnology, technologies } = this.state;

        if (newTechnology) {
            if (!technologies.some((tech) => tech.name === newTechnology.name)) {
                this.setState(
                    (prevState) => ({
                        technologies: [...prevState.technologies, { ...prevState.newTechnology }],
                        newTechnology: {
                            id: null,
                            name: '',
                            tags: []
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


    handleDescriptionChange = (e) => {
        const { value } = e.target;
        this.setState((prevState) => ({
            description: value,
        }));
    }

    handleCourseGenerate = async () => {
        try {
            const { description, technologies } = this.state;

            if (!description) {
                toast.error('Please enter a course description before generating.');
                return;
            }

            if (technologies.length === 0) {
                toast.error('Please add at least one technology before generating.');
                return;
            }
            const OpenAi = require('openai');
            const openai = new OpenAi({ apiKey: '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',
                dangerouslyAllowBrowser: true});

            const prompt = `W języku polskim, stwórz listę kroków niezbędnych do wykonania projektu podanego poniżej, na przykład:
                        Dla 'programowania kalkulatora' powinno zwrócić odpowiedź podobną do:
                        1. Zdefiniuj podstawowe operacje matematyczne jako funkcje (dodawanie, odejmowanie, mnożenie, dzielenie).
                        2. Poproś użytkownika o wybór operacji i zapisz jego wybór.
                        3. Poproś użytkownika o wprowadzenie dwóch liczb i zapisz je jako liczby zmiennoprzecinkowe.
                        4. Na podstawie wyboru użytkownika wykonaj odpowiednią operację matematyczną i wyświetl wynik.
                        5. Opcjonalnie, dodaj obsługę błędów, np. sprawdź, czy użytkownik nie próbuje dzielić przez zero.
                        6. Uruchom program i przetestuj go, aby upewnić się, że działa zgodnie z oczekiwaniami.

                        Zrób to dla poniższego promptu, nie musisz się ograniczać do ilości kroków, użyj technologii ${technologies.map((technology) => technology.name).join(', ')}: \n ${description}`;

            const messages = [
                { role: 'system', content: 'You are a helpful assistant.' },
                { role: 'user', content: prompt },
            ];


            const response = await openai.chat.completions.create({
                model: "gpt-3.5-turbo",
                messages: [{ role: "system", content: "You are a helpful assistant."},
                    { role: "user", content: prompt }],
                temperature: 0,
                max_tokens: 1000,
            });

            const generatedCourse = response.choices[0].message.content;
            // Split the response by line breaks
            const lines = generatedCourse.split('\n');

            // Use the generatedCourse as needed, e.g., display it in the UI
            console.log(lines);
            toast.success('Course generated successfully!');

            // Update the state with the generated lines
            this.setState({
                generatedLines: lines,
            });

            this.setState({
                submittedInfo: {
                    title: this.state.courseTitle,
                    description: this.state.description,
                    technologies: this.state.technologies.map((technology) => technology.id),
                    quests: lines,
                },
            });
        } catch (error) {
            console.error(error);
            toast.error('Failed to generate course. Please try again.');
        }
    };


    handleTitleChange = (e) => {
        const { value } = e.target;
        this.setState({
            courseTitle: value,
        });
    };


    submitToApi = async () => {
        try {
            const { title, description, technologies, questDescriptions } = this.state.submittedInfo;
            console.log(this.state.submittedInfo)
            // Make a POST API call here using fetch or your preferred library
            const response = await axios.post('http://localhost:8080/api/courses', this.state.submittedInfo, {   headers: {
                    'Authorization': `Bearer ${localStorage.getItem('jwtToken')}`,
                    'Content-Type': 'application/json',
                },
            });

            // Check if the API call was successful
            if (response.status === 200) {
                toast.success('Course submitted successfully!');
            }
            else {
                toast.error('Failed to submit course information. Please try again.');
            }
        } catch (error) {
            console.error(error);
            toast.error('Failed to submit course information. Please try again.');
        }
    };


    render() {
        const { technologies, newTechnology, availableTechnologies, generatedLines } = this.state;

        return (
            <div className={'CGmain'}>
                <div className={'CGLeftContainer'}>
                    <input
                        type="text"
                        placeholder="Enter Course Title"
                        value={this.state.courseTitle}
                        onChange={this.handleTitleChange}
                    />
                    <textarea onChange={this.handleDescriptionChange}
                        placeholder={'Enter Course Description'}></textarea>
                    <button onClick={this.handleCourseGenerate}>Generate Course</button>

                    <div>
                        <h2>Generated Course:</h2>
                        <ul>
                            {generatedLines.map((line, index) => (
                                <StepTile key={index} name={line}  />
                            ))}
                        </ul>
                    </div>
                </div>

                <div className={'CGRightContainer'}>
                    <text>Add Technologies</text>

                    <ul>
                        {technologies.map((technology) => (
                            <TechnologyTile key={technology.id} name={technology.name} tags={technology.tags}/>
                        ))}
                    </ul>

                    <div>
                        <select
                            name="name"
                            value={newTechnology.name}
                            onChange={this.handleSelectChange}
                        >
                            <option value="" disabled>
                                Select Technology
                            </option>
                            {availableTechnologies.map((tech) => (
                                <option
                                    key={tech.id}
                                    value={tech.name}
                                    data-id={tech.id}
                                    data-name={tech.name}
                                    data-tags={tech.tags.map(tag => tag.name).join(', ')}  // Extract tag names from the array of objects
                                >
                                    {tech.name}
                                </option>
                            ))}
                        </select>

                        <button onClick={this.handleAddTechnology}>Add Technology</button>

                        <div className={'SubmitButtonContainer'}>
                            <button onClick={this.submitToApi}>Submit</button>
                        </div>
                    </div>
                </div>
                <ToastContainer />
            </div>
        );
    }
}

export default CourseGenerator;
