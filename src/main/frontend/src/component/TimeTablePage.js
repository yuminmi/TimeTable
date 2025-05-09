import AddTable from "./AddTable";
import TimeTable from "./TimeTable";
import SemesterList from "./SemesterList";
import SubjectList from "./SubjectList";
import AddTimeTable from "./AddTimeTable";
import {useState} from "react";
import axios from "axios";

export default function TimeTablePage() {
    const [userName, setUserName] = useState('');
    const [timeTableId, setTimeTableId] = useState('');
    const [timetable, setTimetable] = useState([]);
    /* 시간표 추가 버튼 */
    const [isTableModalOpen, setIsTableModalOpen] = useState(false);
    const openTableModal = () => setIsTableModalOpen(true);
    const closeTableModal = () => setIsTableModalOpen(false);

    /* 시간표 subject 추가 버튼 */

    const [isSubModalOpen, setIsSubModalOpen] = useState(false);
    const openSubModal = () => setIsSubModalOpen(true);
    const closeSubModal = () => setIsSubModalOpen(false);
    const handleAddItem = (newItem) => {
        setTimetable([...timetable, newItem]);
        const fetchTimeTableDetail = async () => {
                try {
                    const response = await axios.post('/api/timetable/detail', {
                        timeTableId: ,
                        courseId: ,
                        weekday: ,
                        location: ,
                        startTime: ,
                        endTime:
                    });
                    setAllTodos(response.data);
                } catch (e) {
                    console.error("fail fetch: ", e);
                }
        };
        setIsSubModalOpen(false);
        alert(`${newItem.subject}가 등록되었습니다.`);
    }

    return (
        <div style={{display:"flex", padding:"210px"}}>
            <div>
            <button onClick={openTableModal}>+</button> // semester 추가버튼
            </div>
            <SemesterList />
            <AddTable isOpen={isTableModalOpen} closeModal={closeTableModal} />
            <SubjectList />
            <div>
            <button onClick={openSubModal}>+</button> // subject 추가버튼
            </div>
            <TimeTable timeItem={timetable}/>
            <AddTimeTable isOpen={isSubModalOpen} closeModal={closeSubModal} onAdd={handleAddItem}/>
        </div>
    );
}