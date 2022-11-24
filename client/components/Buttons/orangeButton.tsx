import styles from "./orangeButton.module.css";

interface OrangeBtnProps {
  name: string;
  onClick?: () => void;
}

const OrangeButton = ({ onClick, name }: OrangeBtnProps) => {
  return (
    <>
      <div className={styles.Wrapper}>
        <button onClick={onClick} className={styles.btn}>
          {name}
        </button>
      </div>
    </>
  );
};

export default OrangeButton;
