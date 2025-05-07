import styles from "./TimeTable.module.css";

export default function TimeTable({timeItem}) {
    function parseTimeToFloat(timeStr) {
        const [h, m] = timeStr.split(":").map(str => parseInt(str, 10)); // 9:30 형식을 10진수로 h=9, m=30저장
        return h + (m/60); // 9 + 30/60 = 9+0.5 => 시간 단위로 계산
    }

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
                          : rowIdx + 9 - 12}
                      </div>
                      {[...Array(7)].map((_, colIdx) => (
                        <div className={styles.cell} key={`cell-${rowIdx}-${colIdx}`}></div>
                      ))}
                    </>
                ))}
                {timeItem.map((item) =>
                 item.times.map((time, index) => {
                    const dayIndex = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"].indexOf(time.day);
                    const startTime = parseTimeToFloat(time.startTime);
                    const endTime = parseTimeToFloat(time.endTime);
                    const HEADER_HEIGHT = 50; // dayIndex 한 줄의 height
                    const CELL_HEIGHT = 50; // cell 한 칸당 height
                    const top = HEADER_HEIGHT + (startTime - 9) * CELL_HEIGHT; // 9시부터 시작이니까 0으로 시작
                    const height = (endTime - startTime) * 50;
                    return <div key={`${item.subject}-${index}`}
                         className={styles.timeBlock}
                         style={{
                            top:`${top}px`,
                            left:`${dayIndex*100+60}px`, // 앞에 시간 width는 60
                            height:`${height}px`,
                            width:'100px',
                            position: 'absolute',
                            backgroundColor:'lightblue',
                            border:'1px solid #999',
                            padding:'4px',
                            boxSizing:'border-box',
                            fontSize:'10px'
                         }}
                    >
                    {console.log(item)}
                        {item.subject}<br/>{item.instructor}<br/>{time.loca}
                    </div>
                 }))}
            </div>

        </div>
    );
}