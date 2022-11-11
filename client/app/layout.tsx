import { Roboto, Noto_Sans_KR } from '@next/font/google';
import { getSession } from 'next-auth/react';
import NavBar from '../components/Navbar/NavBar';
import SessionContainer from '../components/Providers/SessionProvider';
import '../styles/globals.css';

const noto = Noto_Sans_KR({
	weight: '400',
	fallback: ['Roboto'],
	subsets: ['latin'],
});

const layout = async ({ children }: any) => {
	const { segment } = children.props.childProp;
	const session = await getSession();

	return (
		<html className={noto.className}>
			<head>
				<meta name="viewport" content="width=device-width,initial-scale=1" />
				<title>올인원 프로필 링크, 리틀리</title>
			</head>
			<body>
				<SessionContainer session={session}>
					<div className="main">
						{segment !== 'login' ? <NavBar /> : null}
						{children}
					</div>
				</SessionContainer>
			</body>
		</html>
	);
};

export default layout;
