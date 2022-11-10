import Link from 'next/link';
import styles from './QuestionCard.module.css';

const QuestionCard = ({ question }: any) => {
	return (
		<div className={styles.linkCard}>
			<h2>{question.questionTitle}</h2>
			<Link href={`/detail/${question.questionId}`}>
				Go To Detail Question {question.questionId}
			</Link>
		</div>
	);
};

export default QuestionCard;
