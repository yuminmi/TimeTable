import styles from "./TimeTable.module.css";

export default function TimeTable() {
    return (
        <div className={styles.tableBlock}>
            <div className={styles.tableHeader}>
                <h2>Time-Table</h2>
                <p>2025.03.03 ~ 2025.06.23</p>
            </div>
            <div className={styles.timetableGrid}>
                <div className={styles.cell}></div>
                <div className={styles.cell}>Mon</div>
                <div className={styles.cell}>Tue</div>
                <div className={styles.cell}>Wed</div>
                <div className={styles.cell}>Thu</div>
                <div className={styles.cell}>Fri</div>
                <div className={styles.cell}>Sat</div>
                <div className={styles.cell}>Sun</div>
                {[...Array(16)].map((_, rowIdx) => (
                    <>
                      <div className={styles.timeCell}>
                        {rowIdx + 9 <= 12
                          ? rowIdx + 9
                          : rowIdx + 9 > 24
                          ? rowIdx - 15
                          : rowIdx + 9 - 12}
                      </div>
                      {[...Array(7)].map((_, colIdx) => (
                        <div className={styles.cell} key={`cell-${rowIdx}-${colIdx}`}></div>
                      ))}
                    </>
                  ))}
            </div>

        </div>
    );
}