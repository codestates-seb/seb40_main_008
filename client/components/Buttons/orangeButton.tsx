'use client';
import styles from './orangeButton.module.css';

interface OrangeBtnProps {
	name: string;
	handleClick?: () => void;
	type?: any;
}

const OrangeButton = ({
	handleClick = () => {},
	type = 'button',
	name,
}: OrangeBtnProps) => {
	return (
		<>
			<div className={styles.Wrapper}>
				<button
					type={type}
					onClick={handleClick}
					onTouchStart={handleClick}
					className={styles.btn}
				>
					{name}
				</button>
			</div>
		</>
	);
};

export default OrangeButton;
