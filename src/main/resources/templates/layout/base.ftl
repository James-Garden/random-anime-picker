<#--noinspection InjectedReferences-->
<#import '/spring.ftl' as spring>
<#import 'macros/forms/_forms.ftl' as forms>
<#import 'macros/image.ftl' as image>
<#import 'macros/table.ftl' as table>

<#function staticUrl url>
  <#return "/assets/${url}" >
</#function>


<#macro baseTemplate
  pageTitle=""
>
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="${staticUrl("style.css")}">
    <link rel="icon" type="image/x-icon" href="https://spring.io/favicon.svg">
  </head>
    <body>
      <main>
        <#nested>
      </main>
    </body>
  </html>
</#macro>
