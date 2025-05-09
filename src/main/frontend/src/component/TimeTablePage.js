import AddTable from "./AddTable";
import TimeTable from "./TimeTable";
import SemesterList from "./SemesterList";
import SubjectList from "./SubjectList";
import AddTimeTable from "./AddTimeTable";
import {useState} from "react";
import axios from "axios";

export default function TimeTablePage() {
    const [userName, setUserName] = useState('');
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
                    const dayIndex = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"].indexOf(newItem.times[0].day);
                    const response = await axios.post('/api/timetable/detail', {
                        userId: userName,
                        title: newItem.subject,
                        instructor: newItem.instructor,
                        weekday: dayIndex,
                        location: newItem.times[0].loca,
                        startTime: newItem.times[0].startTime,
                        endTime: newItem.times[0].endTime
                    });
                    console.log("서버 응답:", response.data);
                    alert("시간표 저장 완료");
                } catch (e) {
                    console.error("fail fetch: ", e);
                    alert("시간표 저장 실패");
                }
        };
        setIsSubModalOpen(false);
        alert(`${newItem.subject}가 등록되었습니다.`);
    }

    return (
        <div style={{display:"flex", padding:"210px"}}>
            <input type="text" onChange={setUserName} />
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