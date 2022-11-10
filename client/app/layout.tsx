import { Black_Han_Sans } from '@next/font/google';
import NavBar from '../components/Navbar/NavBar';
import '../styles/globals.css';

const BlackHanSans = Black_Han_Sans({ weight: ['400'] });

const layout = ({ children }: any) => {
	const { segment } = children.props.childProp;
	console.log(segment);

	return (
		<html className={BlackHanSans.className}>
			<head>
				<meta name="viewport" content="width=device-width,initial-scale=1" />
				<title>올인원 프로필 링크, 리틀리</title>
			</head>
			<body>
				<div className="main">
					{segment !== 'login' ? <NavBar /> : null}
					{children}
				</div>
			</body>
		</html>
	);
};

export default layout;
