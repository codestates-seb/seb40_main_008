import styles from "./orangeButton.module.css";

interface Prop {
  name: string;
}

const orangeButton = ({ name }: Prop) => {
  return (
    <>
      <div className={styles.Wrapper}>
        <button className={styles.btn}>{name}</button>
      </div>
    </>
  );
};

export default orangeButton;
