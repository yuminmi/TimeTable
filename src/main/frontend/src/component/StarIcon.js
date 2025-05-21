export default function StarIcon ({isMain, updateIsMain}) {
    const imgSrc = isMain ? "/truestar.png" : "/falsestar.png";
    const handleClick = () => {
        if(isMain){
            return;
        }
        updateIsMain(!isMain);
    }
    return (
        <img style={{width: "30px"}} src={imgSrc} alt={"Star"} onClick={handleClick}/>
    )
}