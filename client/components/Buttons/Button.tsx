import styles from "./Button.module.css";

interface Prop {
  name: string;
}

const Button = ({ name }: Prop) => {
  return (
    <>
      <div className={styles.chargeBoardWrapper}>
        <button className={styles.chargeBoard}>{name}</button>
      </div>
    </>
  );
};

export default Button;
