import styles from './LeftList.module.css';
import Semester from "./Semester.js";

export default function SemesterList() {
    return (
        <div className={styles.L_leftList}>
            <h2>Semester</h2>
            <hr />
            <Semester />
            <Semester />
            <Semester />
        </div>
    );
}