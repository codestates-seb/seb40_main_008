import React from 'react';
import styles from './CurriculumInfo.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
	faPencil,
	faTrash,
	faPenToSquare,
} from '@fortawesome/free-solid-svg-icons';
import { ICurriculumContent } from '../../types/contents';
import Image from 'next/image';
import Link from 'next/link';
import Curriculumdelete from '../Buttons/Curriculumdelete';
import { useRouter } from 'next/navigation';
import IUserInfo from '../../types/user/userinfo';

interface CurriculumInfoProps {
	curriculumInfo: ICurriculumContent[];
	contentsId: number;
	role: 'creator' | 'Unpaid_customer' | 'Paid_customer';
	userInfo: IUserInfo;
}

const CurriculumInfo = ({
	role,
	contentsId,
	curriculumInfo,
	userInfo,
}: CurriculumInfoProps) => {
	const router = useRouter();
	return (
		<>
			{curriculumInfo.length === 0 ? (
				<div className={styles.nocurriculumWrapper}>
					업로드된 커리큘럼이 없습니다.
				</div>
			) : (
				curriculumInfo.map((e, index) => (
					<div key={index} className={styles.Wrapper}>
						<div>
							<div className={styles.thumbnail}>
								<Image
									sizes="(max-width: 768px) 100vw,
								(max-width: 1200px) 50vw,
								33vw"
									src={e.thumbnail}
									alt={e.title}
									fill={true}
									style={{ objectFit: 'cover' }}
								/>
							</div>
							<div className={styles.chapterWrapper}>
								<div className={styles.chapterTitle}>
									<p style={{ fontSize: '15px', fontWeight: 'bold' }}>
										{e.chapterOrder}
									</p>
									<div className={styles.chapter}>
										<p
											style={{
												fontSize: '22px',
												fontWeight: 'bold',
											}}
										>
											{e.title}
										</p>
										<div>
											{role == 'creator' ? (
												<>
													<Link
														href={{
															pathname: '/upload/chapter',
															query: {
																slug: 'edit',
																chapterId: e.chapterId,
																contentId: contentsId,
																thumbnail: e.thumbnail,
																title: e.title,
																chapterOrder: e.chapterOrder,
															},
														}}
													>
														<FontAwesomeIcon
															icon={faPenToSquare}
															className={styles.fontimg}
															width={15}
														/>
													</Link>

													<Curriculumdelete
														url={`https://pioneroroom.com/auth/contents/chapter/`}
														Id={e.chapterId}
													/>
												</>
											) : (
												''
											)}
										</div>
									</div>

									{e.uploadClassList.map((e, index) => (
										<div key={index} className={styles.class}>
											<div>
												{userInfo ? (
													<Link
														href={{
															pathname: `/contents/${contentsId}/video/${e.uploadClassId}`,
															query: {
																status: role,
															},
														}}
													>
														<h4 style={{ paddingLeft: "10px", marginTop: "10px", fontWeight: "lighter" }}>{e.title}</h4>
													</Link>
												) : (
													<h4 style={{ paddingLeft: "10px", marginTop: "10px", fontWeight: "lighter" }}>{e.title}</h4>
												)}
											</div>
											<div>
												{role == 'creator' ? (
													<>
														<Curriculumdelete
															url={`https://pioneroroom.com/auth/chapter/lecture/`}
															Id={e.uploadClassId}
														/>
													</>
												) : (
													''
												)}
											</div>
										</div>
									))}

									<div className={styles.addbtnWrapper}>
										{role == 'creator' ? (
											<Link
												href={{
													pathname: '/upload/class',
													query: {
														chapterId: e.chapterId,
														contentsId: contentsId,
													},
												}}
											>
												<button className={styles.addbtn}>
													<FontAwesomeIcon
														icon={faPencil}
														className={styles.fontimg}
													/>
													강의 추가하기
												</button>
											</Link>
										) : (
											''
										)}
									</div>
								</div>
							</div>
						</div>
					</div>
				))
			)}
		</>
	);
};

export default CurriculumInfo;
