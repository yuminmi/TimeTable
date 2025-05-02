import TimeTable from "./TimeTable";
import SemesterList from "./SemesterList";
import SubjectList from "./SubjectList";
import AddTimeTable from "./AddTimeTable";
import {useState} from "react";

export default function TimeTablePage() {
const [isModalOpen, setIsModalOpen] = useState(false);
const openModal = () => setIsModalOpen(true);
const closeModal = () => setIsModalOpen(false);

    return (
        <div style={{display:"flex", padding:"210px"}}>
            <SemesterList />
            <SubjectList />
            <div>
            <button onClick={openModal}>+</button>
            </div>
            <TimeTable />
            <AddTimeTable isOpen={isModalOpen} closeModal={closeModal}/>
        </div>
    );
}