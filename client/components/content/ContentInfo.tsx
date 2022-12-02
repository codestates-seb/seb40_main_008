import styles from "./ContentInfo.module.css";
import OrangeButton from "../Buttons/orangeButton";
import { IContent } from "../../types/contents";
import Image from "next/image";
import { ContentCardWishBtn } from "../Buttons/ContentCardWishBtn";
import { ContentCardFavoriteBtn } from "../Buttons/ContentCardFavoriteBtn";
import Link from "next/link";

interface ContentInfoProps {
  contentInfo: IContent;
}

const ContentInfo = ({ contentInfo }: ContentInfoProps) => {
  const id = contentInfo?.contentsId;
  return (
    <div>
      <div className={styles.thumbnail}>
        <Image
          src={contentInfo?.thumbnail}
          alt={contentInfo?.title}
          fill={true}
        />
      </div>
      <div className={styles.InfoWrapper}>
        <div className={styles.Info}>
          <div>
            <h3>
              {contentInfo?.categories} &nbsp; {contentInfo?.tutorName}
            </h3>
          </div>

          {contentInfo?.role == "Unpaid_customer" ? (
            <ContentCardWishBtn contentId={contentInfo?.contentsId} />
          ) : (
            <ContentCardFavoriteBtn contentId={contentInfo?.contentsId} />
          )}
        </div>

        <div className={styles.classWrapper}>
          <div className={styles.classtitle}>
            <h2>{contentInfo?.title}</h2>
          </div>
          <h2>{contentInfo?.price}</h2>
        </div>
        <h3> 별점{contentInfo?.grade}</h3>
        <div className={styles.btn}>
          <OrangeButton name={"강의 구매하기"} />
          <Link
            href={{
              pathname: "/upload/chapter",
              query: {
                contentId: id,
              },
            }}
          >
            <OrangeButton name={"챕터올리기"} />
          </Link>
        </div>
      </div>
      <hr className={styles.line}></hr>
    </div>
  );
};

export default ContentInfo;
