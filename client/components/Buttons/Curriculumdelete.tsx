import { fetchDelete } from "../../api/fetchDelete";
import { useRouter } from "next/navigation";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";

const Curriculumdelete = (props: { url: string; Id: number }) => {
  const router = useRouter();

  const handleChapterDeleteClick = async (
    url: string,
    Id: number,
    refresh: () => void
  ) => {
    try {
      const status = await fetchDelete(url, Id);
      if (status !== 200) throw new Error("status is not good");
      refresh();
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <>
      <button
        style={{
          border: "0",
          outline: "0",
          color: "white",
          fontSize: "16px",
          background: "transparent",
          marginLeft: "5px",
        }}
        onClick={() =>
          handleChapterDeleteClick(props.url, props.Id, router.refresh)
        }
      >
        <FontAwesomeIcon
          icon={faTrash}
          width={15}
        />
      </button>
    </>
  );
};

export default Curriculumdelete;
