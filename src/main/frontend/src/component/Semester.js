import styles from './LeftList.module.css';

export default function Semester({semester, onClick}) {
    return (
        <div className={styles.L_listItem} onClick={onClick}>
            <div className={styles.L_flag}/>
            <div className={styles.L_title}>{semester.name}</div>
        </div>
    );
}