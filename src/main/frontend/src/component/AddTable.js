import {useState} from "react";

export default function AddTimeTable ({isOpen, closeModal, onAdd}) {
    const [form, setForm] = useState({subject: "", instructor: "", times: []});
    const [timeInput, setTimeInput] = useState({day: "", startTime: "", endTime: "", loca:""});

    const addTimeToForm = () => {
        if(!timeInput.day || !timeInput.startTime || !timeInput.endTime) return;

        setForm({
            ...form,
            times: [...form.times, timeInput]
        });

        setTimeInput({day: '', startTime: '', endTime: '', loca: ''});
    }
    const handleSubmit = (event) => {
        if(!form.subject) return;

        const newItem = {
            subject: form.subject,
            instructor: form.instructor,
            times: form.times
        }
        event.preventDefault();

        onAdd(newItem);
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
                        <h2 style={{display: "inline"}}>테이블추가</h2>
                        <button onClick={closeModal} style={{ border: "none",
                                                              outline: "none",
                                                              backgroundColor: "inherit",
                                                              cursor: "pointer",
                                                              fontSize: "x-large",
                                                              marginRight: "0px"}}>X</button>
                    </div>
                     <hr/>
                     <p>table name : </p>
                     <input type="text" value={form.subject}
                            onChange={(e) => setForm({ ...form, subject: e.target.value})}></input>
                     <br/>
                     <button onClick={handleSubmit}>submit</button>
                </div>

            </div>
        </div>
    );
}