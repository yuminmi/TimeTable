export default function StarIcon ({isMain, updateIsMain}) {
    const imgSrc = isMain ? "/truestar.png" : "/falsestar.png";
    const handleClick = () => {
        updateIsMain(!isMain);
    }
    return (
        <img src={imgSrc} alt={"Star"} onClick={handleClick}/>
    )
}