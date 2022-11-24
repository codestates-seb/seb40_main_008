import styles from "./orangeButton.module.css";

interface Prop {
  name: string;
  onclick?: () => void;
}

const OrangeButton = ({ onclick, name }: Prop) => {
  return (
    <>
      <div className={styles.Wrapper}>
        <button onClick={onclick} className={styles.btn}>
          {name}
        </button>
      </div>
    </>
  );
};

export default OrangeButton;
