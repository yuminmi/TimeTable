import styles from './LeftList.module.css';
import {useState} from "react";
import axios from "axios";

export default function Semester({semester: s, onClick}) {
    const [isDropdownOpen, setIsDropdownOpen] = useState(false);
    const [semester, setSemester] = useState(s);
    const toggleDropdown = () => {
      setIsDropdownOpen(!isDropdownOpen);
    };
    const del = async (e) => {
        e.stopPropagation();
        if (!window.confirm('table을 삭제하시겠습니까?')) return;
        try {
            const response = await axios.delete(`/api/timetable/${semester.id}`);

            if(response.data.success) {
                setSemester({id: 0});
                setIsDropdownOpen(false);
                console.log(response.data.message);
            }
            else {
                console.error('삭제 실패:', response.data.message);
            }
        } catch (error) {
            console.error('에러 발생:', error);
        }
    }

    if(semester.id === 0) {
        return null;
    }
    return (
        <div className={styles.L_listItem} onClick={onClick}>
            <div className={styles.L_flag}/>
            <div className={styles.L_title}>{semester.name}</div>
            <div className={styles.L_menu} onClick={toggleDropdown}>
                <img style={{height: "15px"}} src={"/menu.png"} alt={"menu"}/>
                {isDropdownOpen && (
                    <div className={styles.L_dropdown}>
                        <div className={styles.L_dropdownItem}>수정</div>
                        <div className={styles.L_dropdownItem} onClick={del}>삭제</div>
                    </div>
                )}
            </div>
        </div>
    );
}