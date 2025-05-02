import {useState} from "react";

export default function AddTimeTable ({isOpen, closeModal, onAdd}) {
    const [form, setForm] = useState({subject: "", instructor: "", times: []});
    const [timeInput, setTimeInput] = useState({day: "", startTime: "", endTime: ""});

    const addTimeToForm = () => {
        if(!timeInput.day || !timeInput.startTime || !timeInput.endTime) return;

        setForm({
            ...form,
            times: [...form.times, timeInput]
        });

        setTimeInput({day: '', startTime: '', endTime: ''});
    }
    const handleSubmit = (event) => {
        if(!form.subject || form.times.length === 0) return;

        const newItem = {
            subject: form.subject,
            instructor: form.instructor,
            times: form.times
        }
        event.preventDefault();

        onAdd(newItem);

        alert(`${form.subject}가 등록되었습니다.`);
    }
    return (
        <div style={{display:isOpen?"block": "none",
                     position: "fixed",
                     top: 0,
                     left: 0,
                     width: "100vw",
                     height: "100vh",
                     backgroundColor: "rgba(0,0,0,0.35)"}}>
            <div style={{
                position: "absolute",
                top: "50%",
                left: "50%",
                transform: "translate(-50%, -50%)",
                width: "463px",
                height: "478px",
                backgroundColor: "white",
                borderRadius: "20px",
                border: "solid 1px"
            }}>
                <div style={{padding: "50px"}}>
                    <div>
                        <h2 style={{display: "inline"}}>과목추가</h2>
                        <button onClick={closeModal} style={{ border: "none",
                                                              outline: "none",
                                                              backgroundColor: "inherit",
                                                              cursor: "pointer",
                                                              fontSize: "x-large",
                                                              marginRight: "0px"}}>X</button>
                    </div>
                     <hr/>
                     <p>subject : </p>
                     <input type="text" value={form.subject}
                            onChange={(e) => setForm({ ...form, subject: e.target.value})}></input>
                     <p>teacher : </p>
                     <input type="text"></input>
                     <br />
                     <div>
                        <ul style={{listStyle: "none", padding:0}}>
                            {form.times.map((time, index) => (
                                <li key={index} style={{backgroundColor: "lightgray",
                                                        }}>
                                    {time.day} {time.startTime} ~ {time.endTime}
                                </li>
                            ))}
                        </ul>
                     </div>
                     <select value={timeInput.day} onChange={(e) => setTimeInput({...timeInput, day: e.target.value})}>
                        {['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'].map((d) => (
                            <option key={d} value={d}>{d}</option>
                        ))}
                     </select>
                     <input type="time" value={timeInput.startTime}
                     onChange={(e) => setTimeInput({...timeInput, startTime: e.target.value})} />
                     ~
                     <input type="time" value={timeInput.endTime}
                                          onChange={(e) => setTimeInput({...timeInput, endTime: e.target.value})} />
                     <button onClick={addTimeToForm}>ADD</button>
                     <button onClick={handleSubmit}>submit</button>
                </div>

            </div>
        </div>
    );
}