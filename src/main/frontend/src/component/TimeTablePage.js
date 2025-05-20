import AddTable from "./AddTable";
import TimeTable from "./TimeTable";
import SemesterList from "./SemesterList";
import SubjectList from "./SubjectList";
import AddTimeTable from "./AddTimeTable";
import {useEffect, useState} from "react";
import axios from "axios";

export default function TimeTablePage() {
    const [userName, setUserName] = useState("");
    const [selectedTable, setSelectiveTable] = useState({id: "", name: "", isMain: ""});
    const changeTable = (table) => {
        setSelectiveTable({id: table.id, name: table.name, isMain: table.isMain});
    }
    const [tableList, setTableList] = useState([]);
    const [timeItem, setTimeItem] = useState([]);
    useEffect(() => {
        if(!userName) return;

        const fetchTimeTable = async () => {
            try {
                const response = await axios.get('/api/timetable/' + userName);
                setTableList(response.data.data);
                console.log(response.data.message);
            } catch (e) {
                console.error("fail fetch: ", e);
            }
        };
        const fetchTimeItem = async () => {
            try {
                const timeItemRes = await axios.get('/api/timetable/detail/' + userName);
                setTimeItem(timeItemRes.data.data);
                console.log(timeItemRes.data.message);
            } catch (e) {
                console.error("fail fetch: ", e);
            }
        }
            fetchTimeTable();
            fetchTimeItem();
            console.log("semester: ", tableList);
            console.log("timeitem: ", timeItem);
    }, [userName]);

    /* 시간표 추가 버튼 */
    const [isTableModalOpen, setIsTableModalOpen] = useState(false);
    const openTableModal = () => setIsTableModalOpen(true);
    const closeTableModal = () => setIsTableModalOpen(false);

    /* 시간표 subject 추가 버튼 */

    const [isSubModalOpen, setIsSubModalOpen] = useState(false);
    const openSubModal = () => setIsSubModalOpen(true);
    const closeSubModal = () => setIsSubModalOpen(false);

    const handleAddTable = (newTableName) => {
        console.log("전달받은 테이블 이름:", newTableName, typeof newTableName);
        console.log("유저이름", userName);
        const fetchTimeTable = async () => {
            try {
                const response = await axios.post('/api/timetable', {
                    userId: userName,
                    name: newTableName
                });
                console.log("서버 응답:", response.data.message);
                alert("semester 저장 완료");
            } catch (e) {
                console.error("fail fetch: ", e);
                alert("semester 저장 실패");
            }
        };
        fetchTimeTable();
    }

    const handleAddItem = (newItem) => {
        const fetchTimeTableDetail = async () => {
                try {
                    const courseRes = await axios.post('/api/timetable/detail', {
                        courseDto: {
                             userId: userName,
                             title: newItem.subject,
                             instructor: newItem.instructor,
                             color: "#add8e6"
                        }
                    });

                    const course_id = courseRes.data;

                    for(const time of newItem.times){
                        const dayIndex = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"].indexOf(time.day);
                        const response = await axios.post('/api/timetable/detail', {
                                                tableDetailDto: {
                                                    courseId: course_id,
                                                    timeTableId: selectedTable.id,
                                                    weekday: dayIndex,
                                                    location: time.loca,
                                                    startTime: time.startTime,
                                                    endTime: time.endTime
                                                }
                                            });
                        console.log("시간 블록 저장 응답:", response.data);
                    }
                    alert("시간표 저장 완료");
                } catch (e) {
                    console.error("fail fetch: ", e);
                    alert("시간표 저장 실패");
                }
        };
        fetchTimeTableDetail();
        setIsSubModalOpen(false);
        alert(`${newItem.subject}가 등록되었습니다.`);
    }

    return (
        <div style={{display:"flex", padding:"210px"}}>
            <input type="text"  onChange={(e) => setUserName(e.target.value)} />
            <div>
            <button onClick={openTableModal}>+</button> // semester 추가버튼
            </div>
            <SemesterList semesterList={tableList} changeTable={changeTable} />
            <AddTable isOpen={isTableModalOpen} closeModal={closeTableModal} onAdd={handleAddTable}/>
            <SubjectList subjectList={timeItem}/>
            <div>
            <button onClick={openSubModal}>+</button> // subject 추가버튼
            </div>
            <TimeTable timeItem={timeItem}/>
            <AddTimeTable isOpen={isSubModalOpen} closeModal={closeSubModal} onAdd={handleAddItem}/>
        </div>
    );
}