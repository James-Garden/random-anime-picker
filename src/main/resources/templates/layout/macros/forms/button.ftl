<#macro submitButton
  class="submit-button"
  id="action-button"
  jsEnabled=true
>
  <script type="text/javascript">
    const submitButton = {
        id: "${id}",
        element: null
    };
    const submitButtonEnabled = ${jsEnabled?c};

    if (submitButtonEnabled) {
        window.addEventListener('load', submitButtonSetup)
    }

    function submitButtonSetup() {
        submitButton.element = document.getElementById(submitButton.id);
        submitButton.element.addEventListener('click', disableButton);
    }

    function disableButton() {
        submitButton.element.disabled = true;
    }
  </script>

  <button
    type="submit"
    class="${class}"
    id="${id}"
  >
    <#nested>
  </button>
</#macro>
