import TimeTable from "./TimeTable";
import SemesterList from "./SemesterList";
import AddTimeTable from "./AddTimeTable";
import {useState} from "react";

export default function TimeTablePage() {
const [isModalOpen, setIsModalOpen] = useState(false);
const openModal = () => setIsModalOpen(true);
const closeModal = () => setIsModalOpen(false);

    return (
        <div style={{display:"flex", padding:"210px"}}>
            <SemesterList />
            <TimeTable />
            <button onClick={openModal}>+</button>
            <AddTimeTable isOpen={isModalOpen} closeModal={closeModal}/>
        </div>
    );
}