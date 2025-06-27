import styles from './LeftList.module.css';

export default function Subject({subject}) {
    return (
        <div className={styles.L_listItem}>
            <div className={styles.L_flag}/>
            <div className={styles.L_title}>{subject.title}</div>
        </div>
    );
}