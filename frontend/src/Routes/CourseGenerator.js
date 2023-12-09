import React from 'react';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import '../Styles/CourseGenerator.css';
import TechnologyTile from '../Components/TechnologyTile';
import "openai";
import StepTile from "../Components/StepTile";
class CourseGenerator extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            technologies: [],
            generatedLines: [],
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

                        Zrób to dla poniższego promptu, nie musisz się ograniczać do ilości kroków, użyj technologii ${technologies.map((technology) => technology.title).join(', ')}: \n ${description}`;

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
        } catch (error) {
            console.error(error);
            toast.error('Failed to generate course. Please try again.');
        }
    };



    render() {
        const { technologies, newTechnology, availableTechnologies, generatedLines } = this.state;

        return (
            <div className={'CGmain'}>
                <div className={'CGLeftContainer'}>
                    <textarea onChange={this.handleDescriptionChange}
                        placeholder={'Enter Course Description'}></textarea>
                    <button onClick={this.handleCourseGenerate}>Generate Course</button>

                    <div>
                        <h2>Generated Course:</h2>
                        <ul>
                            {generatedLines.map((line, index) => (
                                <StepTile key={index} name={line} />
                            ))}
                        </ul>
                    </div>
                </div>

                <div className={'CGRightContainer'}>
                    <text>Add Technologies</text>
                    
                    <ul>
                        {technologies.map((technology) => (
                        <TechnologyTile key={technology.title} name={technology.title} />
                    ))}
                    </ul>

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
