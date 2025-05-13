import styles from './LeftList.module.css';

export default function Semester({semester}) {
    return (
        <div className={styles.L_listItem}>
            <div className={styles.L_flag}/>
            <div className={styles.L_title}>{semester.name}</div>
        </div>
    );
}