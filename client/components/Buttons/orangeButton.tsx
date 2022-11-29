import styles from "./orangeButton.module.css";

interface OrangeBtnProps {
  name: string;
  onClick?: () => void;
  type?: any;
}

const OrangeButton = ({ onClick, type, name }: OrangeBtnProps) => {
  return (
    <>
      <div className={styles.Wrapper}>
        <button type={type} onClick={onClick} className={styles.btn}>
          {name}
        </button>
      </div>
    </>
  );
};

export default OrangeButton;
