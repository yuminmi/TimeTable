import TimeTable from "./TimeTable";
import SemesterList from "./SemesterList";
import SubjectList from "./SubjectList";
import AddTimeTable from "./AddTimeTable";
import {useState} from "react";

export default function TimeTablePage() {
const [timetable, setTimetable] = useState([]);
const [isModalOpen, setIsModalOpen] = useState(false);
const openModal = () => setIsModalOpen(true);
const closeModal = () => setIsModalOpen(false);
const handleAddItem = (newItem) => {
    setTimetable([...timetable, newItem]);
    setIsModalOpen(false);
    alert(`${newItem.subject}가 등록되었습니다.`);
}

    return (
        <div style={{display:"flex", padding:"210px"}}>
            <SemesterList />
            <SubjectList />
            <div>
            <button onClick={openModal}>+</button>
            </div>
            <TimeTable timeItem={timetable}/>
            <AddTimeTable isOpen={isModalOpen} closeModal={closeModal} onAdd={handleAddItem}/>
        </div>
    );
}