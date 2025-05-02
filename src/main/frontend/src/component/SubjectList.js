import styles from './LeftList.module.css';
import Subject from "./Subject.js";

export default function SubjectList() {
    return (
        <div className={styles.L_leftList}>
            <h2>Subject</h2>
            <hr />
            <Subject />
            <Subject />
            <Subject />
        </div>
    );
}