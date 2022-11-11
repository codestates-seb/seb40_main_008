import { Noto_Sans_KR } from '@next/font/google';
import NavBar from '../components/Navbar/NavBar';
import SessionContainer from '../components/Providers/SessionProvider';
import '../styles/globals.css';
import { headers } from 'next/headers';
import { getSession } from '../utils/helper/session';
import { unstable_getServerSession } from 'next-auth';

const noto = Noto_Sans_KR({
	weight: '400',
	fallback: ['Roboto'],
	subsets: ['latin'],
});

const layout = async ({ children }: any) => {
	const { segment } = children.props.childProp;
	const session = await getSession(headers().get('cookie') ?? '');
	// const session = await unstable_getServerSession();
	// console.log('🚀 ~ file: layout.tsx ~ line 17 ~ layout ~ session ', session);

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
