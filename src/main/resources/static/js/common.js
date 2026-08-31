/**
 * 指定したIDのカレンダー入力欄に期日制御（過去日不可・手入力不可）を適用する
 * @param {string} inputId - 対象となるinput要素のID
 */
function initDueDateInput(inputId) {
    const dueDateInput = document.getElementById(inputId);
    if (!dueDateInput) return;

    // 今日より前の過去日を選択不可にする
    const today = new Date().toISOString().split('T')[0];
    dueDateInput.min = today;

    // キーボードによる直接入力を無効化（カレンダー選択のみ許可）
    dueDateInput.addEventListener('keydown', (e) => {
        e.preventDefault();
    });
}

// DOM読み込み完了時に実行
document.addEventListener('DOMContentLoaded', () => {
    // 引数としてID名を渡して呼び出す
    initDueDateInput('dueDate');
});