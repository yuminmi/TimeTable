import styles from './LeftList.module.css';

export default function Subject() {
    return (
        <div className={styles.L_listItem}>
            <div className={styles.L_flag}/>
            <div className={styles.L_title}>subject</div>
        </div>
    );
}